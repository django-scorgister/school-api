package net.argus.school.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.argus.school.api.event.SchoolEvent;
import net.argus.school.api.event.SchoolListener;
import net.argus.school.api.event.SchoolResetEntry;

public class BasicSchoolListener implements SchoolListener {
	
	public static final String RM_STUDENTS = "rm-students";
	public static final String RM_MATERIALS = "rm-materials";

	@Override
	public List<SchoolResetEntry> resetRequested(SchoolEvent e) {
		List<SchoolResetEntry> entries = new ArrayList<SchoolResetEntry>();
		
		entries.add(new SchoolResetEntry(RM_STUDENTS, "Remove students", "delete all students from the database"));
		entries.add(new SchoolResetEntry(RM_MATERIALS, "Remove materials", "delete all materials from the database"));
		
		return entries;
	}

	@Override
	public void reset(SchoolEvent e) {
		switch(e.getSchoolEntryId()) {
			case RM_STUDENTS:
				while(Students.ID_REGISTER.getIds().size() != 0) {
					int id = Students.ID_REGISTER.getIds().get(0);
					try {
						Students.removeStudent(id);
						Quantities.removeStudent(id);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				break;
			case RM_MATERIALS:
				while(Materials.ID_REGISTER.getIds().size() != 0) {
					int id = Materials.ID_REGISTER.getIds().get(0);
					try {
						Materials.removeMaterial(id);
						Quantities.removeMaterial(id);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				break;
		}
	}

}
