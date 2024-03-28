const commonUrl = process.env.NEXT_PUBLIC_BASE_URL;

const token = '';

// 결제 비밀번호 입력 키패드 요청
export const getKeyPadImage = async (accountNumberOut: string) => {
  const res = await fetch(`${commonUrl}/api/account/keypad`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      Authorization: `Bearer ${token}`,
    },
    body: JSON.stringify({ accountNumberOut }),
  });
  return res.json();
};

// 결제 비밀번호 확인
export const checkPayPassword = async (
  accountNumberOut: string,
  password: number[],
) => {
  const res = await fetch(`${commonUrl}/api/account/check`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      Authorization: `Bearer ${token}`,
    },
    body: JSON.stringify({ accountNumberOut, password }),
  });
  return res.json();
};
