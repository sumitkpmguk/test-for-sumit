package com.petsalone.core;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class PetsService {

    public List<My_Pet_Class> GetAll(EntityManager entityManager) {
        Query query = entityManager.createQuery("SELECT COUNT(*) FROM My_Pet_Class");
        long x = (long)query.getSingleResult();
        List<My_Pet_Class> y = new ArrayList<My_Pet_Class>();
        for (long i = 0; i < x; i++)
        {
            My_Pet_Class z = entityManager.find(My_Pet_Class.class, i+1);
            y.add(z);
        }

        // seems to be the best way to order by newest first
        List<My_Pet_Class> yy = new ArrayList<My_Pet_Class>();
        for (My_Pet_Class xy : y)
        {
            if (yy.size() == 0)
            {
                yy.add(xy);
            }
            else if (yy.get(0).getMissingSince().isBefore(xy.getMissingSince()))
            {
                List<My_Pet_Class> temp = yy;
                yy = new ArrayList<My_Pet_Class>();
                yy.add(xy);
                for (My_Pet_Class tempitem : temp)
                {
                    yy.add(tempitem);
                }
            }
            else
            {
                yy.add(xy);
            }
        }

        return yy;

    }
}