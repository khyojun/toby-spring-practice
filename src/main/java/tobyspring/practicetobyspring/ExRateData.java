package tobyspring.practicetobyspring;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true) // 없는거 들어있어도 무시
public record ExRateData(String result, Map<String, BigDecimal> rates) {
}
