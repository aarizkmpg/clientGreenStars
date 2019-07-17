package com.client.demo.spring.dao;

import org.hibernate.envers.query.AuditQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AuditQueryUtils {
    private AuditQueryUtils() {
    }

    public static <T> List<AuditQueryResult<T>> getAuditQueryResults(AuditQuery query, Class<T> T) {

        List<?> results = query.getResultList();

        if (results == null) {
            return new ArrayList<>();
        }

        // The AuditReader returns a List of Object[], where the indices are:
        //
        // 0 - The queried entity
        // 1 - The revision entity
        // 2 - The Revision Type
        //
        // We cast it into something useful for a safe access:
        return results.stream()
                // Only use Object[] results:
                .filter(x -> x instanceof Object[])
                // Then convert to Object[]:
                .map(x -> (Object[]) x)
                // Transform into the AuditQueryResult:
                .map(x -> AuditQueryResultUtils.getAuditQueryResult(x, T))
                // And collect the Results into a List:
                .collect(Collectors.toList());
    }
}
