import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit5.HttpsTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

@Provider("My_Provider")
@PactFolder("target/pacts")
public class PactTestProviderExample {

    @BeforeEach
    void before(PactVerificationContext context) {
        context.setTarget(new HttpsTestTarget("localhost",8585));

    }

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    void pactTestTemplate(PactVerificationContext context)
    {
        context.verifyInteraction();
    }

    @State("A request to create a tutorial")
    public void sampleState(){}
}
