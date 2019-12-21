package tripplanner.tripplanner.dao;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tripplanner.tripplanner.model.Item;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.*;

@Repository
public class SearchDaoImpl implements SearchDao {

    // number of items returned for landing page
    private static final int DEFAULT_NUM = 30;

    // singleton design with eager instantiation to ensure thread safe
    @Autowired
    private final EntityManager centityManager;

    @Autowired
    private ItemDao itemDao;

    @Autowired
    public SearchDaoImpl(EntityManager entityManager) {
        this.centityManager = entityManager;
    }

//     for existing database, we need to index the existing ones.
//        public void initializeSearchDaoImpl() throws InterruptedException {
//            try {
//                FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(centityManager);
//                fullTextEntityManager.createIndexer().startAndWait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//        }

    @Override
    public List<Item> searchEvents() {
        CriteriaBuilder builder = centityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root<Item> item = query.from(Item.class);
        query.multiselect(item.get("id"), item.get("review_count"));
        List<Object[]> resultList = centityManager.createQuery(query).getResultList();

        PriorityQueue<Object[]> pq = new PriorityQueue<>(new MyComparator());

        for (Object[] objArr : resultList) {
            if (pq.size() < DEFAULT_NUM) {
                pq.offer(objArr);
                continue;
            }
            if ((int)pq.peek()[1] < (int)objArr[1]) {
                pq.poll();
                pq.offer(objArr);
            }
        }
        List<Item> itemList = new ArrayList<>();
        while (!pq.isEmpty()) {
            Object[] objArr = pq.poll();
            Item tmp = itemDao.findById((String) objArr[0]).get();
            itemList.add(tmp);
        }

        return itemList;
    }

    class MyComparator implements Comparator<Object[]> {

        @Override
        public int compare(Object[] o1, Object[] o2) {
            if ((int) o1[1] == (int) o2[1]) return 0;
            return (int) o1[1] < (int) o2[1] ? -1 : 1;
        }
    }

    @Override
    public List<Item> searchEvents(String keyword) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(centityManager);
        QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Item.class).get();

//        provide the capability of fuzzy search
//        Query luceneQuery = qb.keyword().fuzzy()
//                .withEditDistanceUpTo(1)
//                .withPrefixLength(1)
//                .onFields("name")
//                .matching(keyword).createQuery();
        Query luceneQuery = qb.keyword()
                .onFields("name", "categories.title", "location.address1", "location.zip_code")
//                .onField("name")
                .matching(keyword)
                .createQuery();
        // wrap Lucene query in a javax.persistence.Query
        javax.persistence.Query jpaQuery =
                fullTextEntityManager.createFullTextQuery(luceneQuery, Item.class);

        // execute searchEvents
        List<Item> itemList = null;
        try {
            itemList = jpaQuery.getResultList();
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        return itemList;
    }
}
