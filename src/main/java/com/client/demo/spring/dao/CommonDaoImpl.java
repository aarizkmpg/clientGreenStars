package com.client.demo.spring.dao;

import com.client.demo.spring.model.demo.Attribute;
import com.client.demo.spring.model.demo.Company;
import com.client.demo.spring.model.demo.Product;
import org.hibernate.SessionFactory;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class CommonDaoImpl implements CommonDao {


    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public <T> T getByObjectId(Long id, Class<T> c) {
        return sessionFactory.getCurrentSession().get(c, id);
    }

    @Override
    public long saveObject(Object obj) {
        long id = (long)sessionFactory.getCurrentSession().save(obj);
        return id;
    }

    @Override
    public void updateObject(Object obj) {
        sessionFactory.getCurrentSession().saveOrUpdate(obj);
    }

    @Override
    public void deleteObject(Object obj) {
        sessionFactory.getCurrentSession().delete(obj);
    }

    @Override
    public <T> List<T> listObjects(Class T) {
        @SuppressWarnings("unchecked")
        TypedQuery<T> query = sessionFactory.getCurrentSession().createQuery("from "+T.getName()+" f ORDER BY f.id desc");
        return query.getResultList();
    }

    @Override
    public <T> List<T> listObjects(Class T, int pageNumber) {
        int pageSize = 10;
        @SuppressWarnings("unchecked")
        TypedQuery<T> query = sessionFactory.getCurrentSession().createQuery("from "+T.getName()+" f ORDER BY f.id desc");
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        List<T> list = query.getResultList();
        return list;
    }

    @Override
    @Transactional(readOnly = true, value="transactionManager")
    public <T> List<AuditingObj> listObjectAuditLogs(Class T, Long id, Integer versionNo) {
        //EntityManager entityManager = sessionFactory.getCurrentSession().getEntityManagerFactory().createEntityManager();
        AuditReader auditReader = AuditReaderFactory.get(sessionFactory.getCurrentSession());
        /*
        AuditQuery q = auditReader.createQuery().forRevisionsOfEntity(T, true, true);
        q.add(AuditEntity.id().eq(id));
        q.addOrder(AuditEntity.revisionNumber().desc());

        List<T> audit = q.getResultList();
        */

        AuditQuery q2 = auditReader.createQuery().forRevisionsOfEntity(T, false, true);
        if(id != null){
            q2.add(AuditEntity.id().eq(id));
        }
        if(versionNo != null){
            q2.add(AuditEntity.revisionNumber().eq(versionNo));
        }

        q2.addOrder(AuditEntity.revisionNumber().desc());


        List<AuditQueryResult<T>> llist = AuditQueryUtils.getAuditQueryResults(q2, T);
        List<AuditingObj> list = llist.stream().map(x -> getAuditingHistory(x)).collect(Collectors.toList());
        System.out.println("Auditing Obj list size: "+list.size());
        if(list.size() == 1){
            AuditingObj aObj = list.get(0);
            if(aObj.getObject() instanceof Product){
                System.out.println("");

                Product p = (Product) aObj.getObject();
                System.out.println("p.getCompany().getId():"+p.getCompany().getId());
                p.setCompany(this.getByObjectId(p.getCompany().getId(),Company.class));
                System.out.println("p.getAttributes() : "+p.getAttributes());
                if(p.getAttributes() == null){
                    p.setAttributes(new ArrayList<>());
                }
                /*
                List<AuditingObj> attrList = this.listObjectAuditLogs(Attribute.class,null,aObj.getRevision().intValue());
                if(attrList != null && attrList.size() > 0){
                    attrList.stream().forEach(attr ->{
                        p.getAttributes().add((Attribute) attr.getObject());
                    });
                }
                */
                System.out.println("p.getAttributes() after: "+((Product)aObj.getObject()).getAttributes());
            }
        }
        //em.close();
        return list;

        /*
        AuditQuery q = auditReader.createQuery().forEntitiesAtRevision(T, 2);
        q.traverseRelation(“publisher”, JoinType.LEFT, “p”);
        q.add(AuditEntity.and(
        AuditEntity.or(AuditEntity.property(“title”).ilike(“JPA”, MatchMode.ANYWHERE), AuditEntity.property(“title”).ilike(“Hibernate”, MatchMode.ANYWHERE)),
        AuditEntity.property(“p”, “name”).ilike(“Manning”)));
        q.addOrder(AuditEntity.property(“title”).asc());
        List<T> audit = q.getResultList();
        * */
    }

    private static <R> AuditingObj getAuditingHistory(AuditQueryResult<R> auditQueryResult) {
        System.out.println(auditQueryResult + "  <-------------------");
        System.out.println(auditQueryResult.getEntity().getClass() + "  <-------------------");
        System.out.println(auditQueryResult.getRevision() + "  <-------------------");
        System.out.println(auditQueryResult.getType() + "  <-------------------");
        return new AuditingObj(
                auditQueryResult.getEntity(),
                auditQueryResult.getRevision().getRevisionNumber(),
                auditQueryResult.getType()
        );
    }

}
