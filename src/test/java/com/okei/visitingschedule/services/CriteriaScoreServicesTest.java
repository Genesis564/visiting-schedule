package com.okei.visitingschedule.services;

import com.okei.visitingschedule.Application;
import com.okei.visitingschedule.entity.schedule.CriteriaScore;
import com.okei.visitingschedule.repos.CriteriaScoreRepo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application.properties")
public class CriteriaScoreServicesTest {

    @Autowired
    private CriteriaScoreServices criteriaScoreServices;
    @Test
    public void criteriaSetToSortedList() {
        Set<CriteriaScore> criteriaScoreSet = new HashSet<>();
        criteriaScoreSet.add(new CriteriaScore());
        criteriaScoreSet.add(new CriteriaScore());
        criteriaScoreSet.add(new CriteriaScore());
        List<CriteriaScore> criteriaScores = criteriaScoreServices.sortCriteria(criteriaScoreSet);
        Assert.assertNotNull(criteriaScores);
        Assert.assertTrue(criteriaScoreSet.size()==criteriaScores.size());
    }
}