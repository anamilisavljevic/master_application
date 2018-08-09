/*********************************************************************
 * The Initial Developer of the content of this file is NETCONOMY.
 * All portions of the code written by NETCONOMY are property of
 * NETCONOMY. All Rights Reserved.
 *
 * NETCONOMY Software & Consulting GmbH
 * Hilmgasse 4, A-8010 Graz (Austria)
 * FN 204360 f, Landesgericht fuer ZRS Graz
 * Tel: +43 (316) 815 544
 * Fax: +43 (316) 815544-99
 * www.netconomy.net
 *
 * (c) 2018 by NETCONOMY Software & Consulting GmbH
 *********************************************************************/

package master.project.master_application.dao;

import master.project.master_application.model.UserEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.transaction.Transactional;

@Repository
public interface UserDao extends JpaRepository<UserEntity, Long> {

    @Query(value = "SELECT u FROM UserEntity u")
    List<UserEntity> findAllUsers();

    UserEntity findByEmail(String email);

    @Modifying
    @Transactional
    @Query("UPDATE UserEntity SET name = :name, surname = :surname, country = :country, image = :image "
        + " WHERE email = :email")
    void saveWithoutPassword(@Param("name") String firstName, @Param("surname") String lastName,
        @Param("country") String country, @Param("image") String image,
        @Param("email") String email);
}
