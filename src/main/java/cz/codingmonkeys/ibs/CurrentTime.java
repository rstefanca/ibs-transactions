package cz.codingmonkeys.ibs;

import lombok.NonNull;

/**
 * @author rstefanca
 */
public final class CurrentTime {

	private static CurrentTimeProvider provider = new CurrentTimeProvider() {
		public long getCurrentDateTime() {
			return System.currentTimeMillis();
		}
	};

	public static void setCurrentTimeProvider(@NonNull CurrentTimeProvider currentTimeProvider) {
		provider = currentTimeProvider;
	}

	public static long currentDateTime() {
		return provider.getCurrentDateTime();
	}

	public interface CurrentTimeProvider {
		long getCurrentDateTime();
	}
}
