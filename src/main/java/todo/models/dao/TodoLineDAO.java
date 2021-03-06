package todo.models.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import todo.models.TodoLine;
@Transactional
@Repository
public interface TodoLineDAO extends CrudRepository<TodoLine, Integer>{
	
	public List<TodoLine> findAll();
	public List<TodoLine> findByDate(String date);
	public List<TodoLine> findByDateAndUserId(String date, int id);
	public TodoLine findById(int id);
	public void deleteById(int id);
	
}
