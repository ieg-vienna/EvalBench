package evaluation.evalBench.panel;

import java.awt.BorderLayout;
import java.util.Date;

import javax.swing.JPanel;

import com.toedter.calendar.JCalendar;

import evaluation.evalBench.EvaluationResources;
import evaluation.evalBench.task.DateQuestion;

public class DateQuestionPanelStrategy extends QuestionPanelStrategy {

	//private JPanel answeringPanel;
	private JCalendar calendar;

	public DateQuestionPanelStrategy(DateQuestion aQuestion) {
		super(aQuestion);
	}

	@Override
	public boolean checkForCorrectInput() {

		if (calendar.getDate() != null) {
			Date selectedDate = calendar.getDate();
//			DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
//			String formatedDate = formatDate.format(selectedDate);

			((DateQuestion) super.getQuestion()).setGivenAnswer(selectedDate);
			setErrorMessage("");
			return true;
		}
		
		setErrorMessage(EvaluationResources.getString("datequestion.errorNull"));
		return false;
	}

	@Override
	public JPanel getNewAnsweringPanel() {
		//answeringPanel = new JPanel();
		//answeringPanel.setLayout(new BorderLayout());

		calendar = new JCalendar(new Date(), false);

		answeringPanel.add(calendar, BorderLayout.CENTER);
		return answeringPanel;
	}

	@Override
	public void inputFinished() {
		// TODO Auto-generated method stub

	}
}
