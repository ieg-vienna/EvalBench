package evaluation.evalBench.task;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Abstract base class representing a question a user of a visualization tool has to answer in an evaluation process.
 * It is typically used with a {@link Task}. 
 *
 * Every subclass should provide  <a href="https://jaxb.dev.java.net">jaxb</a> annotations for XML unmarshalling
 *
 * @author XXX
 */
@XmlType(name="questionType")
public abstract class Question {
    
    // TODO optional question

    private String    m_questionId;

    private String    m_questionText;
    
    /**
     * get the correct answer(s) for this question. usually used for logging or journal
     * @return the correct answer(s) for this task
     */
    public abstract Object getCorrectAnswer();

    /**
     * get the given answer(s) of the test person. usually used for logging or journal
     * @return the answer(s) of the test person for this question
     */
    public abstract Object getGivenAnswer();
    
    /**
     * determines if there is a correct answer to this question (e.g., lookup task).
     * @return false, if there is no correct answer (e.g., subjective preference).
     */
    public boolean hasGroundTruth() {
        return getCorrectAnswer() != null;
    }
    
    /**
     * determines if the question was answered correctly
     * @return 0 if the question was not answered correctly, 1 if the question was answered correctly
     * if the evaluation needs a more precise correctness, return any value between 0 and 1.
     */
    public double determineCorrectness() {
        return (getCorrectAnswer() != null 
                && getCorrectAnswer().equals(getGivenAnswer())) ? 1.0 : 0.0;
    }

    /**
     * get the given answer(s) of the test person as a String. usually used for logging or journal
     * @return a String representing the answer(s) of the test person for this question
     */
    public String exportGivenAnswer() {
        return String.valueOf(getGivenAnswer());
    }

    /**
     * get the correct answer(s) for this question as a String. usually used for logging or journal
     * @return a String representing the correct answer(s) for this question
     */
    public String exportCorrectAnswer() {
        return String.valueOf(getCorrectAnswer());
    }

    /**
     * Standard constructor. Initializes question with empty id and text
     */
    protected Question(){
        this("", "");
    }

    /**
     * Constructor for question with given id and text.
     * @param aQuestionId       question id (should be unique)
     * @param aQuestionText     question which is displayed to the test person
     */
    protected Question(String aQuestionId, String aQuestionText){
        m_questionId = aQuestionId;
        m_questionText = aQuestionText;
    }

    // question definition getters & setters

    /**
     * Get the question id (should be unique).
     * 
     * @return the id of the question
     */
    @XmlAttribute(name = "id", required = true)
    public String getQuestionId() {
        return m_questionId;
    }

    /**
     * Set the question id (should be unique).
     * @param questionId the id of the question
     */
    public void setQuestionId(String questionId) {
        this.m_questionId = questionId;
    }

    /**
     * Get the question which is displayed to the test person
     * @return a string which contains the question which is displayed to the test person
     */
    @XmlElement(name = "question-text", required = true)
    public String getQuestionText() {
        return m_questionText;
    }

    /**
     * Set the question which is displayed to the test person
     * @param questionText a string which contains the question which is displayed to the test person
     */
    public void setQuestionText(String questionText) {
        this.m_questionText = questionText;
    }
    
    @Override
    public String toString() {
        return new ToStringBuilder(this).
                append("id", m_questionId).
                append("type", m_questionText).
                toString();
    }
}
