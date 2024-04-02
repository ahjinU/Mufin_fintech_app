import { serverPostFetch } from '@/hooks/useServerFetch';

// 사용자 정보 조회(이름, 이메일 등)
export const getUserInfo = async () => {
  const res = await serverPostFetch({ api: '/user/info' });
  return res?.data;
};
