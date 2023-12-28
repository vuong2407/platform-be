package vn.whatsenglish.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.whatsenglish.customer.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
}
