/*
 * Classname: Task
 *
 * Date: 2018/10/14
 *
 * Author: Dmitrij Yarmolenko
 * E-mail: dsyarmolenko@gmail.com
 *
 */

package ua.edu.sumdu.ta.yarmolenko.pr2;

/**
 * Class Task describes the “task” data type
 */
public class Task{

	private String taskTitle;
	private boolean taskActive;
	private boolean taskRepeated;
	private int taskTime;
	private int taskStart; 
	private int taskEnd; 
	private int taskRepeatInterval;

	/**
     * @param title is a string containing task title
     * @param time is a number defining the task's starting moment
     */
	public Task(String title, int time) {
		setTitle(title);
		setTime(time);
		setActive(false);
	}
	
    /**
     * @param title is a string containing task title
     * @param start is a number defining the starting moment of the alerting about task
     * @param end is a number defining the ending moment of the alerting about task
     * @param repeat is a number defining the time interval for repeating the alert about the task
     */
	public Task(String title, int start, int end, int repeat) {
		setTitle(title);
		setTime(start, end, repeat);
		setActive(false);		
	}

    /**
     * @return String value containing task's title
     */	
	public String getTitle() {
		return this.taskTitle;
	}

    /**
     * @param title is a string to assign to task's title
     */	
	public void setTitle(String title) {
		if (title.length() > 0) {
			taskTitle = title;
		} else {
			System.out.println("Task name must consist of at least one character");
		}
	}

    /**
     * @return boolean value (false for inactive task, and true otherwise)
     */	
	public boolean isActive() {
		return this.taskActive;
	}
	
    /**
     * @param active is a boolean switch 
     */	
	public void setActive(boolean active) {
		taskActive = active;
	}

    /**
     * @return task's time or task's beginning moment for repetitive task's
     */	
	public int getTime() {
		return (this.isRepeated() ? this.taskStart : this.taskTime);
	}

    /**
     * @param time is a number defining the task's starting moment
     */	
	public void setTime(int time) {
		if (time > 0) {
			taskTime = time;
			taskRepeated = false;
		} else {
			System.out.println("Task notification time must be greater than zero");
		}
	}

    /**
     * @return task's time or task's beginning moment for repetitive task's
     */	
	public int getStartTime() {
		return (this.isRepeated() ? this.taskStart : this.taskTime);
	}

    /**
     * @return ending moment for repetitive tasks or task's time for not repetitive tasks
     */	
	public int getEndTime() {
		return (this.isRepeated() ? this.taskEnd : this.taskTime);
	}

    /**
     * @return repetition interval or 0 for not repetitive tasks
     */	
	public int getRepeatInterval() {
		return (this.isRepeated() ? this.taskRepeatInterval : 0);
	}

    /**
     * @param start is a number defining the starting moment of the alerting about task
     * @param end is a number defining the ending moment of the alerting about task
     * @param repeat is a number defining the time interval for repeating the alert about the task
     */
	public void setTime(int start, int end, int repeat) {
		if (start > 0) {
			if (end > start) {
				if (repeat > 0) {
					taskStart = start;
					taskEnd = end;
					taskRepeatInterval = repeat;
					taskRepeated = true;
				} else {
					System.out.println("The time interval after which the task notification "
							+ "must be repeated should be greater than zero");
				}
			} else {
				System.out.println("Time to stop the alert task should be bigger than the "
						+ "start time of the task alert");
			}
		} else {
			System.out.println("The start time of the task alert must be greater than zero");
		}
	}

    /**
     * @return boolean value for repetition status of the task
     */	
	public boolean isRepeated() {
		return this.taskRepeated;
	}

    /**
     * @return string with a description of the task
     */
	public String toString() {
		if (this.isActive()) {
			if (this.isRepeated()) {
				return ("Task \"" + this.getTitle() + "\" from " + this.getStartTime() 
					+ " to " + this.getEndTime() + " every " + this.getRepeatInterval() + " seconds");
			} else {
				return ("Task \"" + this.getTitle() + "\" at " + this.getTime());
			}

		} else {
			return ("Task \"" + this.getTitle() + "\" is inactive");
		}
	}

    /**
     * @param time is a number containing a moment of time
     * @return time of the next alert after the specified time or -1 if it can't happen
     */	
	public int nextTimeAfter(int time) {
		if (this.isActive()) {
            if (this.isRepeated()) {
                if (time < this.getEndTime()) {
                    if (time < this.getStartTime()){
                    	return this.getStartTime();
                    } else {
                    	int t = this.getStartTime() + this.getRepeatInterval();
                    	if (t > this.getEndTime()) {
                    		return this.getEndTime();
                    	} else {
	                    	while ((time >= t) && (t < this.getEndTime())) {
	                    		t = t + this.getRepeatInterval();
	                    	}
	                    	return (t > this.getEndTime()) ? -1 : t;
	                    }
                    }
                } else {
                    return -1;
                }
            } else {
                return (time < getTime()) ? getTime() : -1;
            }
        } else {
            return -1;
        }

	}
	


}