const commonUrl = process.env.NEXT_PUBLIC_BASE_URL;
const baseUrl = 'https://mufin.life/api';

// 사용자 정보 조회(이름, 이메일 등)
export const getUserInfo = async () => {
  const res = await fetch(`${baseUrl}/user/info`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    credentials: 'include',
  });
  return res.json();
};
