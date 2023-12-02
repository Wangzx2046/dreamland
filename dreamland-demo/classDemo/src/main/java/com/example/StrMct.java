package com.example;

public class StrMct {

    public static void main(String[] args) {



        for (int i = 17; i < 61; i++) {
            String a = "INSERT INTO `jtx_mct`.`sys_auth_detail` (`id`, `auth_id`, `privilege_name`, `privilege_type`, `privilege_value`, `privilege_extend`, `remark`, `create_user`, `create_time`, `update_time`, `copy_from`, `copy_id`) " +
                    "VALUES ('2d078701-8f50-11ee-995a-fefcfe298c', '2d070e03-8f50-11ee-995a-fefcfe298c', 'i18n_auth_grant', 15, 1, 'grant', '基础权限-授权', 'auto', 1701328501000, NULL, NULL, NULL);";
            String b = "INSERT INTO `jtx_mct`.`sys_auth_detail` (`id`, `auth_id`, `privilege_name`, `privilege_type`, `privilege_value`, `privilege_extend`, `remark`, `create_user`, `create_time`, `update_time`, `copy_from`, `copy_id`) " +
                    "VALUES ('2d078878-8f50-11ee-995a-fefcfe298c', '2d070e03-8f50-11ee-995a-fefcfe298c', 'i18n_auth_manage', 3, 1, 'manage', '基础权限-管理', 'auto', 1701328501000, NULL, NULL, NULL);";
            String c = "INSERT INTO `jtx_mct`.`sys_auth_detail` (`id`, `auth_id`, `privilege_name`, `privilege_type`, `privilege_value`, `privilege_extend`, `remark`, `create_user`, `create_time`, `update_time`, `copy_from`, `copy_id`)" +
                    " VALUES ('2d0788c2-8f50-11ee-995a-fefcfe298c', '2d070e03-8f50-11ee-995a-fefcfe298c', 'i18n_auth_use', 1, 1, 'use', '基础权限-使用', 'auto', 1701328501000, NULL, NULL, NULL);";

            a = a.replace("2d078701-8f50-11ee-995a-fefcfe298c", "2d078701-8f50-11ee-995a-fefcfe298c" + i)
                    .replace("2d070e03-8f50-11ee-995a-fefcfe298c", "2d070e03-8f50-11ee-995a-fefcfe298c" + i);
            b = b.replace("2d078878-8f50-11ee-995a-fefcfe298c", "2d078878-8f50-11ee-995a-fefcfe298c" + i)
                    .replace("2d070e03-8f50-11ee-995a-fefcfe298c", "2d070e03-8f50-11ee-995a-fefcfe298c" + i);
            c = c.replace("2d0788c2-8f50-11ee-995a-fefcfe298c", "2d0788c2-8f50-11ee-995a-fefcfe298c" + i)
                    .replace("2d070e03-8f50-11ee-995a-fefcfe298c", "2d070e03-8f50-11ee-995a-fefcfe298c" + i);
            System.out.println(a + "\n");
            System.out.println(b + "\n");
            System.out.println(c + "\n");

        }

    }
}
