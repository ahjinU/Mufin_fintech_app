import useFetch from '@/hooks/useFetch';

export default function PasswordApis() {
  const { UsePostFetch } = useFetch();

  // 결제 비밀번호 입력 키패드 요청
  const getKeyPadImage = async (accountNumberOut: string) => {
    const res = await UsePostFetch({
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
    const res = await UsePostFetch({
      api: '/account/check',
      data: { accountNumberOut, password },
    });
    return res;
  };

  return { getKeyPadImage, checkPayPassword };
}
