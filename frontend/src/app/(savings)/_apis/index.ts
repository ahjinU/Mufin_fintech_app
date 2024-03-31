import useFetch from '@/hooks/useFetch';

export default function SavingsApis() {
  const { postFetch } = useFetch();

  // 적금 상품 등록
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
    savingsUuid: string,
    paymentAmount: number,
    paymentDate: number,
  ) => {
    const res = await postFetch({
      api: '/savings/join',
      data: { savingsUuid, paymentAmount, paymentDate },
    });
    return res;
  };

  // 내가 가입한 적금 보기(아이)
  const getAppliedSavingsProduct = async () => {
    const res = await postFetch({
      api: '/savings/search/mine/total',
    });
    return res;
  };

  // 내가 가입한 적금 상세
  const getAppliedSavingsProductDetail = async (accountUuid: string) => {
    const res = await postFetch({
      api: '/savings/search/mine/detail',
      data: { accountUuid },
    });
    return res;
  };

  // 적금 납부하기
  const paySavings = async (accountUuid: string, cnt: number) => {
    const res = await postFetch({
      api: '/savings/deposit',
      data: { accountUuid, cnt },
    });
    return res;
  };

  // 내 자녀의 적금 현황 파악하기(부모)
  const getChildrenSavingsState = async () => {
    const res = await postFetch({
      api: '/savings/search/mychild',
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
    getAppliedSavingsProduct,
    getAppliedSavingsProductDetail,
    paySavings,
    getChildrenSavingsState,
  };
}
