package todo.models.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import todo.models.TodoLine;
import todo.models.User;

@Transactional
@Repository
public interface UserDAO extends CrudRepository<User, Integer>{
	public User findByLogin(String login);
}
