
package ru.roombooking.resetpassword.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.roombooking.resetpassword.model.PasswordConfirmationToken;


import java.util.Optional;

@Repository
public interface PasswordConfirmationTokenRepository extends JpaRepository<PasswordConfirmationToken,Long> {

    Optional<PasswordConfirmationToken> findByToken(String token);


}

