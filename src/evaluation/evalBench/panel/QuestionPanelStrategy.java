package evaluation.evalBench.panel;

import java.awt.BorderLayout;

import evaluation.evalBench.task.Question;
import javax.swing.*;

/**
 * abstract class for a TaskPanelStrategy to be used by an {@link PanelFactory} to create panels for a task
 *
 * @author Stephan Hoffmann
 */
public abstract class QuestionPanelStrategy {

        protected Question m_question;
        public String errorMessage;
        
        protected JPanel answeringPanel;
        private JLabel descriptionField;

        public QuestionPanelStrategy(Question aQuestion){
            m_question = aQuestion;
            setUpDescription();
        }
        
        private void setUpDescription()
        {
        	answeringPanel = new JPanel();
        	answeringPanel.setLayout(new BorderLayout(5, 15));
        	
        	descriptionField = new JLabel(m_question.getQuestionText());
        	
        	answeringPanel.add(descriptionField, BorderLayout.NORTH);
        }

        public Question getQuestion(){
            return this.m_question;
        }

        /**
        * has to be implemented by subclasses
        * @return true if all mandatory fields or other inputs have been entered
        */
        public abstract boolean checkForCorrectInput();

       /**
        * has to be implemented by subclasses
        * @return a JPanel which contains the answering possibilities for the task type (e.g. input fields)
        */
        public abstract JPanel getNewAnsweringPanel();


        // TODO unnecessary? this can be performed by check for correct input
       /**
        * has to be implemented by subclasses
        * responsible to set the answers for a specific task
        */
        public abstract void inputFinished();

}
