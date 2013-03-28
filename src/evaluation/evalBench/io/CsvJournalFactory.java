package evaluation.evalBench.io;

import evaluation.evalBench.session.EvaluationSession;
/**
 * Implementation of the abstract {@link JournalFactory} that returns a CSVJournal to the client. 
 * @author XXX
 *
 */
public class CsvJournalFactory implements JournalFactory {

	public EvaluationSessionJournal journalForSession(EvaluationSession session) {

		return new CsvJournal(session);
	}

}
