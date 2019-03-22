import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by cmccarthy on 3/20/19.
 */
public class ContendedSync {
	public static void main(String[] args) throws InterruptedException {
		Integer poolSize;
		Long iters;
		if (args.length < 3) {
			System.out.println("Args: poolsize, iterations. Ex: 4 100");
			System.out.println("Using Defaults: poolsize 4 iterations 10000000");
			poolSize = 4;
			iters = 10000000L;
		} else {
			poolSize = Integer.valueOf(args[1]);
			iters = Long.valueOf(args[2]);
		}
		System.out.println(String.format("Executing with pool size %d and iterations %d", poolSize, iters));
		Executor exec = Executors.newFixedThreadPool(poolSize);
		while (true) {
			for (int i = 0; i < iters; i++) {
				exec.execute(new MyRunnable());
			}
			System.out.println(String.format("Completed %d iterations", iters));
			Thread.sleep(15000);
		}
	}
}
