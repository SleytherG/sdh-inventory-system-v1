package sdh.store.inventory.manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sdh.store.inventory.manager.user.dto.PermissionDTO;
import sdh.store.inventory.manager.user.dto.RoleDTO;
import sdh.store.inventory.manager.user.dto.RoleEnum;
import sdh.store.inventory.manager.user.dto.UserDTO;
import sdh.store.inventory.manager.user.mappers.UserMapper;

import java.util.Set;

@SpringBootApplication
@MapperScan({
		"sdh.store.inventory.manager.product.mappers",
		"sdh.store.inventory.manager.user.mappers",
		"sdh.store.inventory.manager.category.mappers",
		"sdh.store.inventory.manager.supplier.mappers"
})
public class SdhStoreInventoryManagerV1Application {

	public static void main(String[] args) {
		SpringApplication.run(SdhStoreInventoryManagerV1Application.class, args);
	}

//	@Bean
//	CommandLineRunner init(UserMapper userMapper) {
//		return args -> {
//			/* CREATE PERMISSIONS */
//			PermissionDTO createPermission = PermissionDTO.builder().permissionName("CREATE").build();
//			PermissionDTO readPermission = PermissionDTO.builder().permissionName("READ").build();
//			PermissionDTO updatePermission = PermissionDTO.builder().permissionName("UPDATE").build();
//			PermissionDTO deletePermission = PermissionDTO.builder().permissionName("DELETE").build();
//			PermissionDTO refactorPermission = PermissionDTO.builder().permissionName("REFACTOR").build();
//
//			/* CREATE ROLES */
//
//			RoleDTO roleAdmin = RoleDTO
//					.builder()
//					.roleName(RoleEnum.ADMIN.name())
//					.permissions(Set.of(createPermission, readPermission, updatePermission, deletePermission))
//					.build();
//
//			RoleDTO roleEmployee = RoleDTO
//					.builder()
//					.roleName(RoleEnum.EMPLOYEE.name())
//					.permissions(Set.of(readPermission))
//					.build();
//
//			UserDTO userSleyther = UserDTO
//					.builder()
//					.username("sleyther")
//					.password("1234")
//					.isEnabled(true)
//					.accountNoExpired(true)
//					.accountNoLocked(true)
//					.credentialsNoExpired(true)
//					.roles(Set.of(roleAdmin))
//					.build();
//
//			UserDTO userDaniel = UserDTO
//					.builder()
//					.username("daniel")
//					.password("1234")
//					.isEnabled(true)
//					.accountNoExpired(true)
//					.accountNoLocked(true)
//					.credentialsNoExpired(true)
//					.roles(Set.of(roleEmployee))
//					.build();
//
//			userMapper.saveAll(userSleyther, userDaniel);
//
//
//		};
//	}

}
