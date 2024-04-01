import useFetch from '@/hooks/useFetch';

export default function BookApis() {
  const { postFetch, postImageFetch } = useFetch();

  const getMonthBookDetail = async (data: {
    startDate: null | string;
    endDate: null | string;
    childUuid: null | string;
  }) => {
    const res = await postFetch({
      api: '/allowance/calender-detail',
      data: data,
    });
    return res;
  };

  const getMonthBook = async (data: {
    startDate: null | string;
    endDate: null | string;
    childUuid: null | string;
  }) => {
    const res = await postFetch({
      api: '/allowance/calender',
      data: data,
    });
    return res;
  };

  const getDayBook = async (data: {
    date: string;
    childUuid: null | string;
  }) => {
    const res = await postFetch({
      api: '/allowance/day',
      data: data,
    });
    return res;
  };

  const postDayCash = async (data: {
    date: string | undefined;
    usage: string | undefined;
    amount: number | undefined;
  }) => {
    const res = await postFetch({
      api: '/allowance/cash',
      data: data,
    });
    return res;
  };

  const postReceipt = async (data: FormData) => {
    console.log(data);
    const res = await postImageFetch({
      api: '/allowance/receipt/save',
      data: data,
    });
    return res;
  };

  const getOneTransaction = async (data: {
    transactionUUID: string;
    type: string;
  }) => {
    const res = await postFetch({
      api: '/allowance/detail',
      data: data,
    });
    return res;
  };

  const postMemo = async (data: {
    type: string;
    transactionUuid: string;
    memo: string;
  }) => {
    const res = await postFetch({
      api: '/allowance/memo',
      data: data,
    });
    return res;
  };

  return {
    getMonthBookDetail,
    getMonthBook,
    getDayBook,
    postDayCash,
    postReceipt,
    getOneTransaction,
    postMemo,
  };
}
