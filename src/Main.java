import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Main {
	
	public static BigDecimal getDecimalValue(Map<?, ?> data, String key) {
		BigDecimal value = null;
		if (data == null) {
			return value;
		}
		Object object = data.get(key);
		if (object instanceof BigDecimal) {
			return ((BigDecimal) object);
		}
		if (object instanceof Double) {
			Double tmp = (Double) object;

			return new BigDecimal(Double.toString(tmp));
		}
		return value;
		
	}
	
	public static void main(String[] args) {
		double dd = 12.10;
		Map data = new HashMap();
		data.put("dd", dd);
		
		System.out.println(Main.getDecimalValue(data, "dd"));
	}
}