package spring.storm.applicationContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.storm.application.SpringApplication;
import spring.storm.exception.StormException;
/**
 * 
 * @author agarg
 * Singleton Class to create ApplicationContext which will be shared across Spouts and Bolts
 * running on a Worker Machine in a single VM.
 */
public class StormContext {

  private static final Logger LOG = LoggerFactory.getLogger(StormContext.class);
  
  private volatile static StormContext stormContext;
  private AnnotationConfigApplicationContext context;


  public static StormContext getStormContext() {
    LOG.info("Enter getStormContext ");
    if (stormContext == null) {
      synchronized (StormContext.class) {
        if (stormContext == null) {
          AnnotationConfigApplicationContext appContext = null;
          LOG.info("Inside Synchrnized block for Storm Context");
          try {
            appContext = new AnnotationConfigApplicationContext(SpringApplication.class);
            stormContext = new StormContext();
            stormContext.setApplicationContext(appContext);
          } catch (Exception ex) {
            ex.printStackTrace();
            LOG.error("Could not create Spring ApplicationContext due to ", ex);
            throw new StormException(ex.getMessage(), ex.getCause());
          }
          stormContext = new StormContext();
          stormContext.setApplicationContext(appContext);
        }
      }
    }
      LOG.info("Got the Storm Context ");
      return stormContext;
  }


  public AnnotationConfigApplicationContext getApplicationContext() {
    return context;
  }

  public void setApplicationContext(AnnotationConfigApplicationContext appContext) {
    this.context = appContext;
  }
}
