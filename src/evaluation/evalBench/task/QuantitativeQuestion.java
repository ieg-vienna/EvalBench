package evaluation.evalBench.task;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class representing a quantitative question a user of a visualization tool has to
 * answer in an evaluation process. E.g. What value has variable x on date y?
 * 
 * @author Stephan Hoffmann, David Bauer
 */
@XmlRootElement(name = "quantitativeTask")
public class QuantitativeQuestion extends Question {

	/**
	 * correct value or null (if there is no ground truth)
	 */
	private Double m_correctValue = null;
	
	/**
	 * tolerance; if null a small tolerance will be used
	 */
	private Double m_tolerance;

	private Double m_answeredValue;

	private String m_unit;

	private boolean m_isInteger;
	private boolean m_useSpinner;

	/**
	 * minimum possible value (otherwise error message)
	 */
	private double m_minimum;

    /**
     * maximum possible value (otherwise error message)
     */
    private double m_maximum;
    
	/**
	 * label displayed near the minimum of the slider 
	 */
	private String m_minimumString;
	
	/**
	 * label displayed near the maximum of the slider
	 */
	private String m_maximumString;

	private double m_stepsize;

	/**
	 * Standard constructor, initializes the object with empty taskId,
	 * taskDescription and sets the correct value and tolerance to zero
	 */
	public QuantitativeQuestion() {
		super("", "");
		this.setCorrectValueAndTolerance(Double.NEGATIVE_INFINITY, "", 0.0);
	}

	/**
	 * Constructor that takes all necessary information for the task
	 * 
	 * @param aQuestionId
	 *            the unique id for the task
	 * @param aQuestionText
	 *            the description of the task for the test person
	 * @param aCorrectValue
	 *            the correct answer for the task (i.e. a specific double value)
	 * @param aUnit
	 *            the unit of the quantitative value (e.g. mg/dl)
	 * @param aTolerance
	 *            the tolerance which should be used to calculate the
	 *            correctness
	 */
	public QuantitativeQuestion(String aQuestionId, String aQuestionText,
			double aCorrectValue, String aUnit, double aTolerance) {
		super(aQuestionId, aQuestionText);
		this.setCorrectValueAndTolerance(aCorrectValue, aUnit, aTolerance);
	}

	/**
	 * 
	 * @return true if the answer has to be an integer, false if the task
	 *         accepts an answer in double format
	 */
	@XmlElement(name = "is-integer", defaultValue="true")
	public boolean isInteger() {
		return m_isInteger;
	}

	/**
	 * set the task to accept only integer values
	 * 
	 * @param setInteger
	 *            if true, the task accepts only integer type as an answer,
	 *            false if double type
	 */
	public void setInteger(boolean setInteger) {
		this.m_isInteger = setInteger;
	}

	public void setStepsize(double stepsize) {
		m_stepsize = stepsize;
	}

    @XmlElement(name = "step-size", defaultValue="1.0")
	public double getStepsize() {
		return m_stepsize;
	}

	public void setMaximum(double maximum) {
		m_maximum = maximum;
	}

	public double getMaximum() {
		return m_maximum;
	}

	public void setMinimum(double minimum) {
		m_minimum = minimum;
	}

	public double getMinimum() {
		return m_minimum;
	}

	public String getMaximumString() {
		return m_maximumString;
	}

	public void setMaximumString(String maximumString) {
		m_maximumString = maximumString;
	}

	public String getMinimumString() {
		return m_minimumString;
	}

	public void setMinimumString(String minimumString) {
		m_minimumString = minimumString;
	}

    /**
     * @param useSpinner
     *            - If there should be a JSpinner instead of a slider control
     *            this has to be TRUE
     */
    @XmlElement(name = "use-spinner", defaultValue = "true")
    public void setUseSpinner(boolean useSpinner) {
        this.m_useSpinner = useSpinner;
    }

	/**
	 * @return useSpinner - returns the value of useSpinner
	 */
	public boolean getUseSpinner() {
		return m_useSpinner;
	}

	/**
	 * set the correct answer (i.e. specific double value) and the tolerance
	 * which should be used to calculate the correctness
	 * 
	 * @param aValue
	 * @param aTolerance
	 */
	public void setCorrectValueAndTolerance(double aValue, double aTolerance) {
		this.setCorrectAnswer(aValue);

		if (aTolerance >= 0.0) {
			this.m_tolerance = aTolerance;
		} else {
			this.m_tolerance = 0.0;
		}
	}

	/**
	 * set the correct answer (i.e. specific double value) and the tolerance
	 * which should be used to calculate the correctness and the corresponding
	 * unit
	 * 
	 * @param aValue
	 * @param aUnit
	 * @param aTolerance
	 */
	public void setCorrectValueAndTolerance(double aValue, String aUnit,
			double aTolerance) {
		this.setUnit(aUnit);
		this.setCorrectValueAndTolerance(aValue, aTolerance);
	}

	/**
	 * get the unit of the quantitative value
	 * 
	 * @return
	 */
	@XmlElement(name = "unit", defaultValue = "")
	public String getUnit() {
		return m_unit;
	}

	/**
	 * set the unit of the quantitative value
	 * 
	 * @param aUnit
	 */
	public void setUnit(String aUnit) {
		this.m_unit = aUnit;
	}

    /**
     * get the quantitative value the test person has given
     * 
     * @return answer or <tt>null</tt> if the question was not answered yet
     */
	@Override
	@XmlElement
    public Double getGivenAnswer() {
        return m_answeredValue;
    }

	/**
	 * set the answer for this task
	 * 
	 * @param m_answeredValue
	 */
	public void setGivenAnswer(Double m_answeredValue) {
		this.m_answeredValue = m_answeredValue;
	}
	
	// TODO export...Answer round to reasonable precision and include unit

	/**
	 * get the correct value for this task
	 * 
	 * @return
	 */
	@Override
	@XmlElement(name = "correctValue")
	public Double getCorrectAnswer() {
		return m_correctValue;
	}

	/**
	 * set the correct value for this task which will be used for the
	 * correctness calculation along with the tolerance
	 * 
	 * @param m_correctValue
	 */
	public void setCorrectAnswer(Double m_correctValue) {
		this.m_correctValue = m_correctValue;
	}

	/**
	 * get the tolerance value for this task which will be used for the
	 * correctness calculation along with the correct value
	 * 
	 * @return
	 */
	public Double getTolerance() {
		return m_tolerance;
	}

	/**
	 * set the tolerance value for this task which will be used for the
	 * correctness calculation along with the correct value
	 * 
	 * @param m_tolerance
	 */
	public void setTolerance(Double m_tolerance) {
		this.m_tolerance = m_tolerance;
	}

	/**
	 * Calculates binary task correctness (answer within tolerance returns 1,
	 * else 0)
	 */
	@Override
	public double determineCorrectness() {
	    if (m_correctValue == null || m_answeredValue == null) { 
	        return 0;
	    }
	    
	    double tolerance = (m_tolerance != null) ? m_tolerance : 1E-5;  
		if (Math.abs(m_correctValue - m_answeredValue) <= tolerance) {
			return 1;
		}

		return 0;
	}
}
