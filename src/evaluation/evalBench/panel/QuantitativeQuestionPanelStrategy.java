package evaluation.evalBench.panel;

import evaluation.evalBench.EvaluationResources;
import evaluation.evalBench.task.QuantitativeQuestion;

import javax.swing.*;

import java.awt.*;

/**
 * Subclass of {@link QuantitativeQuestionPanelStrategy} Task Panel Strategy for
 * a {@link QuantitativeQuestion} with a JTextField for the answer
 * 
 * @author Stephan Hoffmann
 */
public class QuantitativeQuestionPanelStrategy extends QuestionPanelStrategy {
	//private JTextField textField;

	private JComponent inputComponent;

	private JLabel minLabel;
	private JLabel maxLabel;


	/**
	 * constructor taking a {@link QuantitativeQuestion}
	 * 
	 * @param aQuestion
	 *            {@link QuantitativeQuestion}
	 */
	public QuantitativeQuestionPanelStrategy(QuantitativeQuestion aQuestion) {
		super(aQuestion);

	}

	/**
	 * checks for correct input for this task and sets the answer value
	 * 
	 * @return false if empty false if {@link QuantitativeQuestion} is integer
	 *         and input was not an integer false if
	 *         {@link QuantitativeQuestion} is not integer and the answer does
	 *         not contain a double/float value
	 */
	@Override
	public boolean checkForCorrectInput() {

		QuantitativeQuestion quantTask = (QuantitativeQuestion) this.getQuestion();

		if (quantTask.isInteger()) {
			try {
				if (quantTask.getUseSpinner())
					quantTask.setGivenAnswer(Double
							.parseDouble(((JSpinner) inputComponent).getValue()
									.toString()));
				else
					quantTask.setGivenAnswer((double) ((JSlider) inputComponent)
							.getValue());

				this.errorMessage = "";
				return true;

			} catch (Exception e) {
				this.errorMessage = EvaluationResources.getString("quantitativequestion.errorInt");
				return false;
			}
		} else {
			try {
				if (quantTask.getUseSpinner())
					quantTask.setGivenAnswer(Double
							.parseDouble(((JSpinner) inputComponent).getValue()
									.toString()));
				else {
					quantTask.setGivenAnswer(Double.parseDouble(String
							.valueOf(((JSlider) inputComponent).getValue())));
				}

				this.errorMessage = "";
				return true;

			} catch (Exception e) {
				this.errorMessage = EvaluationResources.getString("quantitativequestion.errorDouble");
			}
		}

		return false;

		// if (!(textField.getText().length() > 0)) {
		//
		// this.errorMessage = (EvaluationResources
		// .getString("quanttaskpanel.errorNumber"));
		// return false;
		// } else {
		// QuantitativeQuestion quantTask = (QuantitativeQuestion)
		// this.getTask();
		//
		// if (quantTask.isInteger()) {
		// try {
		// Integer.parseInt(textField.getText());
		//
		// } catch (NumberFormatException ex) {
		// this.errorMessage = (EvaluationResources
		// .getString("quanttaskpanel.errorInt"));
		// return false;
		// }
		// } else {
		// try {
		// Double.parseDouble(textField.getText());
		// //
		// quantTask.setAnsweredValue(Double.parseDouble(textField.getText()));
		// } catch (NumberFormatException ex) {
		// this.errorMessage = (EvaluationResources
		// .getString("quanttaskpanel.errorNumber"));
		// return false;
		// }
		// }
		//
		// }
		//
		// return true;
	}

	private void initInputComponent(boolean useSpinner) {
		QuantitativeQuestion quantitativeTask = (QuantitativeQuestion) super
				.getQuestion();

		if (!useSpinner) {
			inputComponent = new JSlider();
			((JSlider) inputComponent).setMinimum((int) quantitativeTask
					.getMinimum());
			((JSlider) inputComponent).setMaximum((int) quantitativeTask
					.getMaximum());

			((JSlider) inputComponent).setValue(((int) quantitativeTask
					.getMinimum() + (int) quantitativeTask.getMaximum()) / 2);

			minLabel = new JLabel(quantitativeTask.getMinimumString());
			maxLabel = new JLabel(quantitativeTask.getMaximumString());

		} else {
			inputComponent = new JSpinner();
			((JSpinner) inputComponent).setModel(new SpinnerNumberModel(
					(quantitativeTask.getMaximum() + quantitativeTask
							.getMinimum()) / 2, quantitativeTask.getMinimum(),
					quantitativeTask.getMaximum(), quantitativeTask
							.getStepsize()));

		}
	}

	/**
	 * provides an answering field (JPanel) with a JTextField
	 * 
	 * @return an answering field
	 */
	@Override
	public JPanel getNewAnsweringPanel() {

		initInputComponent(((QuantitativeQuestion) super.getQuestion())
				.getUseSpinner());

		JPanel innerPanel = new JPanel();
		innerPanel.setLayout(new BorderLayout());

		if (minLabel != null)
			innerPanel.add(minLabel, BorderLayout.WEST);
		innerPanel.add(inputComponent, BorderLayout.CENTER);
		if (maxLabel != null)
			innerPanel.add(maxLabel, BorderLayout.EAST);

		// textField = new JTextField(5);
		//
		// textField.setMinimumSize(new Dimension(50, 20));
		// textField.setPreferredSize(new Dimension(50, 20));
		// textField.setMaximumSize(new Dimension(100,
		// Short.MAX_VALUE));
		//
		// JPanel answeringPanel = new JPanel();
		//
		// answeringPanel.setLayout(new BoxLayout(answeringPanel,
		// BoxLayout.X_AXIS));
		//
		// {
		// JLabel jLabel1 = new JLabel();
		//
		// QuantitativeQuestion quantTask =
		// (QuantitativeQuestion)this.getTask();
		// jLabel1.setText(quantTask.getUnit());
		//
		// answeringPanel.add(Box.createRigidArea(new Dimension(5, 5)));
		// answeringPanel.add(textField);
		// answeringPanel.add(Box.createRigidArea(new Dimension(15, 5)));
		// answeringPanel.add(jLabel1);
		// answeringPanel.add(Box.createHorizontalGlue());
		//
		//
		//
		// jLabel1.setBackground(Color.WHITE);
		// }

		answeringPanel.add(innerPanel, BorderLayout.CENTER);
		
		return answeringPanel;
	}

	@Override
	public void inputFinished() {

		// if (quantTask.isInteger()){
		// try{
		// quantTask.setAnsweredValue(Integer.parseInt(textField.getText()));
		// }catch (NumberFormatException ignored){
		//
		// }
		// }else {
		// try{
		//
		// quantTask.setAnsweredValue(Double.parseDouble(textField.getText()));
		// }catch (NumberFormatException ignored){
		//
		// }
		// }
	}
}
