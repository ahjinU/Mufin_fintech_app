import useFetch from '@/hooks/useFetch';

export default function SavingsApis() {
  const { postFetch } = useFetch();

  // 적금 상품 등록 (완료)
  const registerSavingsProduct = async (
    name: string,
    period: number,
    interest: number,
  ) => {
    const res = await postFetch({
      api: '/savings/register',
      data: { name, period, interest },
    });
    return res;
  };

  // 적금 상품 조회
  const getSavingsProductList = async () => {
    const res = await postFetch({
      api: '/savings/search/total',
    });
    return res;
  };

  // 적금 상품 삭제
  const removeSavingsProduct = async (savingsUuid: string) => {
    const res = await postFetch({
      api: '/savings/delete',
      data: { savingsUuid },
    });
    return res;
  };

  // 적금 중도 해지
  const cancelSavings = async (accountUuid: string) => {
    const res = await postFetch({
      api: '/savings/cancel',
      data: { accountUuid },
    });
    return res;
  };

  // 적금 만기 해지
  const terminateSavings = async (accountUuid: string) => {
    const res = await postFetch({
      api: '/savings/terminate',
      data: { accountUuid },
    });
    return res;
  };

  // 적금 상품 가입
  const applySavingsProduct = async (
    savingsUuId: string,
    paymentAmount: number,
    paymentDate: number,
  ) => {
    const res = await postFetch({
      api: '/savings/join',
      data: { savingsUuId, paymentAmount, paymentDate },
    });
    return res;
  };

  return {
    registerSavingsProduct,
    getSavingsProductList,
    removeSavingsProduct,
    applySavingsProduct,
    terminateSavings,
    cancelSavings,
  };
}
