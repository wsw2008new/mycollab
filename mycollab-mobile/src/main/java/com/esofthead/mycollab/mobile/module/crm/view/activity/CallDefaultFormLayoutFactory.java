/**
 * This file is part of mycollab-mobile.
 *
 * mycollab-mobile is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-mobile is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-mobile.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.mobile.module.crm.view.activity;

import com.esofthead.mycollab.form.view.builder.DateTimeDynaFieldBuilder;
import com.esofthead.mycollab.form.view.builder.DynaSectionBuilder;
import com.esofthead.mycollab.form.view.builder.TextAreaDynaFieldBuilder;
import com.esofthead.mycollab.form.view.builder.TextDynaFieldBuilder;
import com.esofthead.mycollab.form.view.builder.type.DynaForm;
import com.esofthead.mycollab.form.view.builder.type.DynaSection;
import com.esofthead.mycollab.form.view.builder.type.DynaSection.LayoutType;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.1
 * 
 */
public class CallDefaultFormLayoutFactory {
	public static final DynaForm defaultForm;

	static {
		defaultForm = new DynaForm();

		DynaSection callSection = new DynaSectionBuilder()
				.layoutType(LayoutType.TWO_COLUMN).orderIndex(0)
				.header("Call Information").build();

		callSection.fields(new TextDynaFieldBuilder().fieldName("subject")
				.displayName("Subject").fieldIndex(0).mandatory(true).build());

		callSection.fields(new TextDynaFieldBuilder().fieldName("status")
				.displayName("Status").fieldIndex(1).build());

		callSection.fields(new DateTimeDynaFieldBuilder()
				.fieldName("startdate").displayName("Start Date & Time")
				.fieldIndex(2).build());

		callSection.fields(new TextDynaFieldBuilder().fieldName("typeid")
				.displayName("Related To").fieldIndex(3).build());

		callSection.fields(new TextDynaFieldBuilder()
				.fieldName("durationinseconds").displayName("Duration")
				.fieldIndex(4).build());

		callSection.fields(new TextDynaFieldBuilder().fieldName("purpose")
				.displayName("Purpose").fieldIndex(5).build());

		defaultForm.sections(callSection);

		DynaSection descSection = new DynaSectionBuilder()
				.layoutType(LayoutType.ONE_COLUMN).orderIndex(1)
				.header("Description").build();
		descSection.fields(new TextAreaDynaFieldBuilder()
				.fieldName("description").displayName("Description")
				.fieldIndex(0).build());
		defaultForm.sections(descSection);

		DynaSection resultSection = new DynaSectionBuilder()
				.layoutType(LayoutType.ONE_COLUMN).orderIndex(2)
				.header("Result").build();
		resultSection.fields(new TextAreaDynaFieldBuilder()
				.fieldName("result").displayName("Result").fieldIndex(0)
				.build());

		defaultForm.sections(resultSection);
	}

	public static DynaForm getForm() {
		return defaultForm;
	}
}
