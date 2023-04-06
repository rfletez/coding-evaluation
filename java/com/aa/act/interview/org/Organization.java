package com.aa.act.interview.org;

import java.util.Optional;

public abstract class Organization {

	private Position root;
	
	public Organization() {
		root = createOrganization();
	}
	
	protected abstract Position createOrganization();
	
	/**
	 * hire the given person as an employee in the position that has that title
	 * 
	 * @param person
	 * @param title
	 * @return the newly filled position or empty if no position has that title
	 */
	public Optional<Position> hire(Name person, String title) {
		Random rand = new Random();
        int random_int = rand.nextInt(1000);
        
        Employee employee = new Employee(random_int, person);
		
        Optional<Position> position = Optional.of(new Position(title));
          
        if(!position.get().getDirectReports().contains(title)) {
        	
        	if(!position.get().isFilled()) {
        		
        		Position job = new Position(title, employee);
        		position = Optional.of(job);
        		
        		position.get().addDirectReport(job);
            }
        }
		
		return position;
	}

	@Override
	public String toString() {
		return printOrganization(root, "");
	}
	
	private String printOrganization(Position pos, String prefix) {
		StringBuffer sb = new StringBuffer(prefix + "+-" + pos.toString() + "\n");
		for(Position p : pos.getDirectReports()) {
			sb.append(printOrganization(p, prefix + "\t"));
		}
		return sb.toString();
	}
}
