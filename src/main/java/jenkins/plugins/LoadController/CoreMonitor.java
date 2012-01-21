package jenkins.plugins.LoadController;

import hudson.Extension;
import hudson.model.Computer;
import hudson.model.Hudson;
import hudson.node_monitors.AbstractNodeMonitorDescriptor;
import hudson.node_monitors.NodeMonitor;
import hudson.remoting.Callable;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

import net.sf.json.JSONObject;

import org.kohsuke.stapler.StaplerRequest;


/**
 * Extract no of available cores on Unix slaves.
 * @author Fatih Degirmenci
 */

/**
 * This class provides an additional Cores column in the node page.
 * It may only be seen by administrators.
 */
public class CoreMonitor extends NodeMonitor {

    /** {@inheritDoc} */
    @Override
    public final String getColumnCaption() {
        // Hide this column from non-admins
        return Hudson.getInstance().hasPermission(Hudson.ADMINISTER) ? super
                .getColumnCaption() : null;
    }

    /**
     * Descriptor for the Monitor.
     */
    @Extension
    public static final AbstractNodeMonitorDescriptor<String> DESCRIPTOR = new AbstractNodeMonitorDescriptor<String>() {

        /** {@inheritDoc} */
        protected String monitor(Computer c) throws IOException,
                InterruptedException {
            return c.getChannel().call(new MonitorTask());
        }

        /** {@inheritDoc} */
        public CoreMonitor newInstance(StaplerRequest req, JSONObject formData) throws FormException {
            return new CoreMonitor();
        }

        /** {@inheritDoc} */
        public String getDisplayName() {
            return "# Cores";
        }
    };

    /**
     * Task which returns the No of Cores.
     */
    static final class MonitorTask implements Callable<String, RuntimeException> {
        private static final long serialVersionUID = 1L;

        /**
        * Get no of cores.
        */
        public String call() {
            final OperatingSystemMXBean opsysMXbean = ManagementFactory
                    .getOperatingSystemMXBean();
            return String.format("%d", opsysMXbean.getAvailableProcessors());
        }
    }
}
