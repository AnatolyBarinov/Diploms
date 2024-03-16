package iiot.istok.repository;


import iiot.istok.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("select u from UserEntity u join fetch u.authorities where u.username=:username")
    Optional<UserEntity> findByUsernameFetchAuthorities(String username);

    @Query("select u.id from UserEntity u where u.username = :username")
    Optional<Long> findIdByUsername(@Param("username") String username);
}