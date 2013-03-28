package evaluation.evalBench.session;

import java.util.ArrayList;

import evaluation.evalBench.io.OutputManager;

/**
 * represents a group of evaluation sessions {@link EvaluationSession} for a test person
 */
public class EvaluationSessionGroup {

    // TODO add counter and methods like hasNext() next() --> simplify evaluationSessionDidFinish()
    
    private String m_participantId;

    private ArrayList<EvaluationSession> m_SessionList = new ArrayList<EvaluationSession>();

    private EvaluationSession m_activeSubSession;
    
    private OutputManager m_outputManager;

    /**
     * constructor, creates a new array list of evaluation sessions ({@link EvaluationSession}) for a specific
     * test person
     * @param participantId
     */
    public EvaluationSessionGroup(String participantId){
        m_participantId = participantId;
        this.m_outputManager = new OutputManager(m_participantId);
    }

    /**
     *
     * @return the id of the test person
     */
    public String getParticipantId() {
        return m_participantId;
    }


    /**
     * adds a evaluation session to the group {@link EvaluationSession}
     * @param aEvaluationSession
     */
    public void addSession(EvaluationSession aEvaluationSession){
        aEvaluationSession.setSessionGroup(this);
        m_SessionList.add(aEvaluationSession);
    }

    /**
     * starts the specified evaluation session, if available
     * @param aSubSession
     */
    public void startSubSession(EvaluationSession aSubSession){
        if (m_SessionList.indexOf(aSubSession) != -1){
            m_activeSubSession = aSubSession;
            m_activeSubSession.startSession();
        }

    }


    /**
     * provides the currently active session in the group
     * @return active session
     */
    public EvaluationSession getActiveSubSession(){
        return m_activeSubSession;
    }

    /**
     * provides the complete session list
     * @return session list
     */
    public ArrayList<EvaluationSession> getSessionList() {
        return m_SessionList;
    }

    /**
     * @return the output manager
     */
    public OutputManager getOutputManager() {
        return m_outputManager;
    }
}
