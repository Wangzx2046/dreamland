public class StrMct2 {

    public static void main(String[] args) {


        for (int i = 17; i < 21; i++) {
            String a = " INSERT INTO `jtx_mct`.`sys_auth_detail` (`id`, `auth_id`, `privilege_name`, `privilege_type`, `privilege_value`, `privilege_extend`, `remark`, `create_user`, `create_time`, `update_time`, `copy_from`, `copy_id`) " +
                    "VALUES ('20bb4f0e-720e-11ee-ae35-fefcfe298c', '0aa4a9fa-7303-11ee-ae35-fefcfe298c', 'i18n_auth_export', 3, 1, 'export', '基础权限-导出', 'auto', 98111549000, NULL, NULL, NULL);";
            String b = " INSERT INTO `jtx_mct`.`sys_auth_detail` (`id`, `auth_id`, `privilege_name`, `privilege_type`, `privilege_value`, `privilege_extend`, `remark`, `create_user`, `create_time`, `update_time`, `copy_from`, `copy_id`) " +
                    "VALUES ('20bb509e-720e-11ee-ae35-fefcfe298c', '0aa4a9fa-7303-11ee-ae35-fefcfe298c', 'i18n_auth_grant', 15, 1, 'grant', '基础权限-授权', 'auto', 98111549000, NULL, NULL, NULL);";
            String c = " INSERT INTO `jtx_mct`.`sys_auth_detail` (`id`, `auth_id`, `privilege_name`, `privilege_type`, `privilege_value`, `privilege_extend`, `remark`, `create_user`, `create_time`, `update_time`, `copy_from`, `copy_id`) " +
                    "VALUES ('20bb50ee-720e-11ee-ae35-fefcfe298c', '0aa4a9fa-7303-11ee-ae35-fefcfe298c', 'i18n_auth_manage', 5, 1, 'manage', '基础权限-管理', 'auto', 98111549000, NULL, NULL, NULL);";
            String d = " INSERT INTO `jtx_mct`.`sys_auth_detail` (`id`, `auth_id`, `privilege_name`, `privilege_type`, `privilege_value`, `privilege_extend`, `remark`, `create_user`, `create_time`, `update_time`, `copy_from`, `copy_id`) " +
                    "VALUES ('20bb511e-720e-11ee-ae35-fefcfe298c', '0aa4a9fa-7303-11ee-ae35-fefcfe298c', 'i18n_auth_view', 1, 1, 'view', '基础权限-查看', 'auto', 98111549000, NULL, NULL, NULL);";

            a = a.replace("20bb4f0e-720e-11ee-ae35-fefcfe298c", "20bb4f0e-720e-11ee-ae35-fefcfe298c" + i)
                    .replace("0aa4a9fa-7303-11ee-ae35-fefcfe298c", "0aa4a9fa-7303-11ee-ae35-fefcfe298c" + i);
            b = b.replace("20bb509e-720e-11ee-ae35-fefcfe298c", "20bb509e-720e-11ee-ae35-fefcfe298c" + i)
                    .replace("0aa4a9fa-7303-11ee-ae35-fefcfe298c", "0aa4a9fa-7303-11ee-ae35-fefcfe298c" + i);
            c = c.replace("20bb50ee-720e-11ee-ae35-fefcfe298c", "20bb50ee-720e-11ee-ae35-fefcfe298c" + i)
                    .replace("0aa4a9fa-7303-11ee-ae35-fefcfe298c", "0aa4a9fa-7303-11ee-ae35-fefcfe298c" + i);
            d = d.replace("20bb511e-720e-11ee-ae35-fefcfe298c", "20bb511e-720e-11ee-ae35-fefcfe298c" + i)
                    .replace("0aa4a9fa-7303-11ee-ae35-fefcfe298c", "0aa4a9fa-7303-11ee-ae35-fefcfe298c" + i);
            System.out.println(a + "\n");
            System.out.println(b + "\n");
            System.out.println(c + "\n");
            System.out.println(d + "\n");

        }

    }
}
