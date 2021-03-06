/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.ui.chart.Key;
import com.esofthead.mycollab.ui.chart.PieChartWrapper;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.core.utils.BeanUtility;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.events.TaskEvent;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.spring.AppContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import org.jfree.data.general.DefaultPieDataset;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.2.0
 */
@ViewComponent
public class TaskAssigneeChartWidget extends PieChartWrapper<TaskSearchCriteria> implements ITaskAssigneeChartWidget {
    public TaskAssigneeChartWidget() {
        super(350, 280);
    }

    @Override
    protected DefaultPieDataset createDataset() {
        // create the dataset...
        final DefaultPieDataset dataset = new DefaultPieDataset();
        if (!groupItems.isEmpty()) {
            for (GroupItem item : groupItems) {
                String assignUser = (item.getGroupid() != null) ? item.getGroupid() : "";
                String assignUserFullName = item.getGroupid() == null ? AppContext.getMessage(GenericI18Enum.OPT_UNDEFINED) :
                        item.getGroupname();
                if (assignUserFullName == null || "".equals(assignUserFullName.trim())) {
                    assignUserFullName = StringUtils.extractNameFromEmail(assignUser);
                }
                dataset.setValue(new Key(assignUser, assignUserFullName), item.getValue());
            }
        }
        return dataset;
    }

    @Override
    protected List<GroupItem> loadGroupItems() {
        ProjectTaskService taskService = AppContextUtil.getSpringBean(ProjectTaskService.class);
        return taskService.getAssignedTasksSummary(searchCriteria);
    }

    @Override
    public void clickLegendItem(String key) {
        TaskSearchCriteria cloneSearchCriteria = BeanUtility.deepClone(searchCriteria);
        cloneSearchCriteria.setAssignUser(StringSearchField.and(key));
        EventBusFactory.getInstance().post(new TaskEvent.SearchRequest(this, cloneSearchCriteria));
    }
}
