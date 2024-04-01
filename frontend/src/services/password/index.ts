import useFetch from '@/hooks/useFetch';

export default function PasswordApis() {
  const { postFetch } = useFetch();

  // 결제 비밀번호 입력 키패드 요청
  const getKeyPadImage = async (accountNumberOut: string) => {
    const res = await postFetch({
      api: '/account/keypad',
      data: { accountNumberOut },
    });
    return res;
  };

  // 결제 비밀번호 확인
  const checkPayPassword = async (
    accountNumberOut: string,
    password: number[],
  ) => {
    const res = await postFetch({
      api: '/account/check',
      data: { accountNumberOut, password },
    });
    return res;
  };

  // 계좌 생성 시 결제 비밀번호 키패드 요청
  const getAccountKeyPadImage = async () => {
    const res = await postFetch({
      api: '/account/create',
    });
    return res;
  };

  // 계좌 생성 시 비밀번호 설정
  const setPayPassword = async (password: number[]) => {
    const res = await postFetch({
      api: '/account/password',
      data: { password },
    });
    return res;
  };

  return {
    getKeyPadImage,
    checkPayPassword,
    getAccountKeyPadImage,
    setPayPassword,
  };
}
