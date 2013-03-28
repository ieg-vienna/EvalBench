package evaluation.evalBench.task;

import java.util.Date;

/**
 * @author XXX
 * 
 */
import javax.xml.bind.annotation.XmlElement;

public class DateQuestion extends Question {

    private Date m_correctAnswer = null;
	private Date m_givenAnswer = null;

	public DateQuestion() {
		this("", "");
	}

	public DateQuestion(String aQuestionId, String aQuestionText) {
		super(aQuestionId, aQuestionText);
	}

    @Override
    @XmlElement
    public Date getCorrectAnswer() {
        return m_correctAnswer;
    }

    public void setCorrectAnswer(Date m_correctAnswer) {
        this.m_correctAnswer = m_correctAnswer;
    }

    @Override
    @XmlElement
    public Date getGivenAnswer() {
        return m_givenAnswer;
    }

    public void setGivenAnswer(Date m_givenAnswer) {
        this.m_givenAnswer = m_givenAnswer;
    }
}
