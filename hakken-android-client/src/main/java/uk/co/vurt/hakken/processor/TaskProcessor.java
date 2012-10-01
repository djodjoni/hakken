package uk.co.vurt.hakken.processor;

import java.util.List;

import uk.co.vurt.hakken.domain.JSONUtil;
import uk.co.vurt.hakken.domain.task.Page;
import uk.co.vurt.hakken.domain.task.TaskDefinition;
import uk.co.vurt.hakken.providers.Task;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

public class TaskProcessor {

	private static final String TAG = "TaskProcessor";
	
	private static final String[] PROJECTION = new String[] {
//		Task.Definitions._ID,
		Task.Definitions.JSON
	};
	
//	private static final int COLUMN_INDEX_TASKDEFINITION_ID = 0;
//	private static final int COLUMN_INDEX_TASKDEFINITION_JSON = 1;
	
	private TaskDefinition taskDefinition;
	
	
	public TaskProcessor(ContentResolver contentResolver, Uri taskUri){
		Log.d(TAG, "Instantiating with uri: " + taskUri);
		Cursor definitionCursor = contentResolver.query(taskUri, TaskProcessor.PROJECTION, null, null, null);
		if(definitionCursor != null){
			definitionCursor.moveToFirst();
//			init(definitionCursor.getString(COLUMN_INDEX_TASKDEFINITION_JSON));
			init(definitionCursor.getString(0));
			definitionCursor.close();
		}
	}
	
	public void init(String taskDefinitionJson){
		setTaskDefinition(JSONUtil.getInstance().parseTaskDefinition(taskDefinitionJson));
	}

	public TaskDefinition getTaskDefinition() {
		return taskDefinition;
	}

	public void setTaskDefinition(TaskDefinition taskDefinition) {
		this.taskDefinition = taskDefinition;
	}
	
	public String getTaskName(){
		if(taskDefinition != null){
			return taskDefinition.getName();
		}else {
			return null;
		}
	}
	
	public List<Page> getPages(){
		return taskDefinition.getPages();
	}
}
