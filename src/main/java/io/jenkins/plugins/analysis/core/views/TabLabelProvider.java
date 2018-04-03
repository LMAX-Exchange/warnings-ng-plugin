package io.jenkins.plugins.analysis.core.views;

import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import edu.hm.hafner.analysis.Issues;

/**
 * Provides localized labels for the different categories of issues.
 *
 * @author Ullrich Hafner
 */
public class TabLabelProvider {
    private final Issues<?> issues;

    /**
     * Creates a new {@link TabLabelProvider}.
     *
     * @param issues
     *         the issues to show in the tabs
     */
    public TabLabelProvider(final Issues<?> issues) {
        this.issues = issues;
    }

    public String getIssues() {
        return Messages.Tab_Issues();
    }

    public String getTools() {
        return Messages.Tab_Tools();
    }

    public String getToolName() {
        return Messages.Tab_Tool();
    }

    public String getModules() {
        return Messages.Tab_Modules();
    }

    public String getModuleName() {
        return Messages.Tab_Module();
    }

    public String getFiles() {
        return Messages.Tab_Files();
    }

    public String getFileName() {
        return Messages.Tab_File();
    }

    public String getCategories() {
        return Messages.Tab_Categories();
    }

    public String getCategory() {
        return Messages.Tab_Category();
    }

    public String getTypes() {
        return Messages.Tab_Types();
    }

    public String getType() {
        return Messages.Tab_Type();
    }

    public String getDetails() {
        return Messages.Tab_Details();
    }

    /**
     * Returns the package column title using the suffix of the affected files.
     *
     * @return the package column title
     */
    public final String getPackageName() {
        return getPackageOrNamespace(Messages.Tab_Package(), Messages.Tab_Namespace(), Messages.Tab_Folder());
    }

    /**
     * Returns the package category title using the suffix of the affected files.
     *
     * @return the package category title
     */
    public final String getPackages() {
        return getPackageOrNamespace(Messages.Tab_Packages(), Messages.Tab_Namespaces(), Messages.Tab_Folders());
    }

    private String getPackageOrNamespace(final String packageText, final String nameSpaceText, final String fallback) {
        if (issues.isNotEmpty()) {
            Set<String> fileTypes = issues.getProperties(
                    issue -> StringUtils.substringAfterLast(issue.getFileName(), "."));
            if (fileTypes.contains("cs")) {
                return nameSpaceText;
            }
            else if (fileTypes.contains("java")) {
                return packageText;
            }
        }
        return fallback;
    }
}
