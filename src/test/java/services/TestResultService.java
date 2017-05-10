package services;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import ua.test.dao.interfaces.AnswerDao;
import ua.test.services.ServiceFactory;

import java.util.*;

import static org.mockito.Mockito.mock;

public class TestResultService {
    private final static Double EXP = 0.00001;
    private final AnswerDao answerMock = mock(AnswerDao.class);

    @Test
    public void getTheBestResult() {
        Map testResult = new HashMap(){{
            put(9, new LinkedList(){{
                add(26);
            }});
            put(10, new LinkedList(){{
                add(28);
                add(31);
            }});
        }};
        when(answerMock.findRightByQuestionId(9)).thenReturn(new LinkedList(){{
            add(26);}});
        when(answerMock.findRightByQuestionId(10)).thenReturn(new LinkedList(){{
            add(28);
            add(31);
        }});
        double mark = ServiceFactory.getInstance().getResultService().giveMark(testResult);
        double expectedMark = 100;

        assertTrue(Math.abs(mark - expectedMark) < EXP);
    }

    @Test
    public void getZeroResult() {
        Map testResult = new HashMap(){{
            put(9, new LinkedList(){{
                add(101);
            }});
            put(10, new LinkedList(){{
                add(28);
                add(32);
            }});
        }};
        when(answerMock.findRightByQuestionId(9)).thenReturn(new LinkedList(){{
            add(26);}});
        when(answerMock.findRightByQuestionId(10)).thenReturn(new LinkedList(){{
            add(28);
            add(31);
        }});
        double mark = ServiceFactory.getInstance().getResultService().giveMark(testResult);
        double expectedMark = 0;

        assertTrue(Math.abs(mark - expectedMark) < EXP);
    }

    @Test
    public void getHalfResult() {
        Map testResult = new HashMap(){{
            put(9, new LinkedList(){{
                add(45);
            }});
            put(10, new LinkedList(){{
                add(28);
                add(31);
            }});
        }};
        when(answerMock.findRightByQuestionId(9)).thenReturn(new LinkedList(){{
            add(26);}});
        when(answerMock.findRightByQuestionId(10)).thenReturn(new LinkedList(){{
            add(28);
            add(31);
        }});
        double mark = ServiceFactory.getInstance().getResultService().giveMark(testResult);
        double expectedMark = 50;

        assertTrue(Math.abs(mark - expectedMark) < EXP);
    }

    @Test
    public void getThirdPartOfResult() {
        Map testResult = new HashMap(){{
            put(9, new LinkedList(){{
                add(45);
            }});
            put(10, new LinkedList(){{
                add(28);
                add(31);
            }});
            put(11, new LinkedList(){{
                add(29);
                add(30);
                add(32);
            }});
        }};
        when(answerMock.findRightByQuestionId(9)).thenReturn(new LinkedList(){{
            add(26);}});
        when(answerMock.findRightByQuestionId(10)).thenReturn(new LinkedList(){{
            add(28);
            add(32);
        }});
        when(answerMock.findRightByQuestionId(10)).thenReturn(new LinkedList(){{
            add(29);
            add(30);
            add(32);
        }});
        double mark = ServiceFactory.getInstance().getResultService().giveMark(testResult);
        double expectedMark = 33.33;

        assertTrue(Math.abs(mark - expectedMark) < EXP);
    }

}
