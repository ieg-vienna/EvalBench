package evaluation.evalBench.panel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import evaluation.evalBench.EvaluationResources;
import evaluation.evalBench.task.LikertskalaQuestion;

/**
 * @author David
 *
 */
public class LikertskalaQuestionPanelStrategy extends QuestionPanelStrategy {

	private JLabel minJLabel, maxJLabel;

	private ButtonGroup radioGroup;
	private ArrayList<JRadioButton> radioList;
	private JRadioButton button;

	/**
	 * @param aQuestion
	 */
	public LikertskalaQuestionPanelStrategy(LikertskalaQuestion aQuestion) {
		super(aQuestion);
	}

	@Override
	public boolean checkForCorrectInput() {

		JRadioButton selectedButton = null;

		for (JRadioButton button : radioList) {
			if (button.isSelected())
				selectedButton = button;
		}

		if (selectedButton != null) {
			try {
				((LikertskalaQuestion) super.getQuestion()).setGivenAnswer(Integer
						.parseInt(selectedButton.getName()));
				setErrorMessage("");
				return true;
			} catch (Exception e) {
				setErrorMessage(EvaluationResources.getString("likertskalaquestion.errorInt"));
				return false;
			}

		} else {
			setErrorMessage(EvaluationResources.getString("likertskalaquestion.errorButton"));
			return false;
		}

	}

	@Override
	public JPanel getNewAnsweringPanel() {
		JPanel innerPanel = new JPanel();
		innerPanel.setLayout(new BorderLayout());

		radioGroup = new ButtonGroup();
		radioList = new ArrayList<JRadioButton>();

		LikertskalaQuestion question = (LikertskalaQuestion) super.getQuestion();

		minJLabel = new JLabel(question.getLeftLabel());
		innerPanel.add(minJLabel, BorderLayout.WEST);

		JPanel innerInnerPanel = new JPanel();
		innerInnerPanel.setLayout(new FlowLayout());

		for (int i = 0; i < question.getCountOptions(); i++) {
			button = new JRadioButton("");
			button.setName(String.valueOf(i));

			radioGroup.add(button);
			radioList.add(button);

			innerInnerPanel.add(button);
		}

		innerPanel.add(innerInnerPanel);
		maxJLabel = new JLabel(question.getRightLabel());
		innerPanel.add(maxJLabel, BorderLayout.EAST);

		answeringPanel.add(innerPanel, BorderLayout.CENTER);
		
		return answeringPanel;
	}

	@Override
	public void inputFinished() {
		// TODO Auto-generated method stub

	}
}
