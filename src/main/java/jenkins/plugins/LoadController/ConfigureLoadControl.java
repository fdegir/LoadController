package jenkins.plugins.LoadController;

import hudson.DescriptorExtensionList;
import hudson.Extension;
import hudson.model.Descriptor;
import jenkins.model.Jenkins;
import hudson.model.Node;
import hudson.model.TaskListener;
import hudson.slaves.NodeProperty;
import hudson.slaves.NodePropertyDescriptor;
import hudson.slaves.NodeSpecific;
import java.io.IOException;
import java.util.ArrayList;

import org.kohsuke.stapler.DataBoundConstructor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * 
 *
 * @author Fatih Degirmenci
 */
public class ConfigureLoadControl extends NodeProperty<Node> {
    @Extension
    public static class DescriptorImpl extends NodePropertyDescriptor {
        @Override
        public String getDisplayName() {
            return Messages.ConfigureLoadControl_displayName();
        }
    }
}
