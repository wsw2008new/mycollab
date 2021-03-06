/**
 * This file is part of mycollab-app-community.
 *
 * mycollab-app-community is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-app-community is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-app-community.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.configuration.service;

import com.esofthead.mycollab.configuration.IDeploymentMode;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * @author MyCollab Ltd
 * @since 5.2.2
 */
@Service
@Order(value=1)
public class DeploymentMode implements IDeploymentMode {
    @Override
    public boolean isDemandEdition() {
        return false;
    }

    @Override
    public boolean isCommunityEdition() {
        return true;
    }

    @Override
    public boolean isPremiumEdition() {
        return false;
    }
}
