package hello;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface BannerRepository extends MongoRepository<Banner, String> {
	public Banner findByBannerId(String bannerId);
}
