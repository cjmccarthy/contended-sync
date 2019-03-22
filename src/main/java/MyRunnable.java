import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Segment;
import com.newrelic.api.agent.Trace;

import java.util.Random;

/**
 * Created by cmccarthy on 3/20/19.
 */
public class MyRunnable implements Runnable {

	private static int wait = 1;
	private static final Random rand = new Random();

	@Trace(dispatcher = true)
	public void run() {
		Segment segment = NewRelic.getAgent().getTransaction().startSegment("MyRunnableSyncWait");

		synchronized (MyRunnable.class) {
			segment.end();
			try {
				wait = Math.min(wait + 1, 100);
				Thread.sleep(rand.nextInt(wait));
			} catch (InterruptedException ignored) {
			}
		}
	}

}
