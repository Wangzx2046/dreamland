import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.api.trace.propagation.W3CTraceContextPropagator;
import io.opentelemetry.context.Scope;
import io.opentelemetry.sdk.OpenTelemetry;
import io.opentelemetry.sdk.resources.Resource;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.export.BatchExportingSpanProcessor;
import io.opentelemetry.sdk.trace.export.ConsoleSpanExporter;
import io.opentelemetry.sdk.trace.export.SpanExporter;
import io.opentelemetry.semconv.resource.attributes.ResourceAttributes;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static final String SERVICE_NAME = "my-service";

    public static void main(String[] args) {
        // Create a tracer and OpenTelemetry resource
        Tracer tracer = getTracer();
        Resource resource = Resource.create(ResourceAttributes.SERVICE_NAMEpickup).merge(ResourceAttributes.SERVICE_VERSION("1.0.0"));

        // Create a span with the current trace context
        Span span = tracer.spanBuilder("my-span")
                .setParent(Context.current().with(
                        W3CTraceContextPropagator.getInstance().extract(Context.current().traceContext())))
                .startSpan();

        // Add attributes to the span
        span.setAttribute("http.status_code", 200);
        span.setAttribute("http.method", "GET");
        span.setAttribute("http.url", "http://example.com");

        // Perform some tracing activity
        try (Scope scope = span.makeCurrent()) {
            logger.info("Performing some tracing activity");
            TimeUnit.MILLISECONDS.sleep(1000);
        }

        // End and export the span
        span.end();
    }

    private static Tracer getTracer() {
        // Create a SdkTracerProvider with a console span exporter for debugging purposes
        SpanProcessor spanProcessor = BatchExportingSpanProcessor.create(
                ConsoleSpanExporter.builder().build());
        return OpenTelemetry.builder()
                .setResource(resource)
                .addSpanProcessor(spanProcessor)
                .build().getTracer(SERVICE_NAME);
    }
}
