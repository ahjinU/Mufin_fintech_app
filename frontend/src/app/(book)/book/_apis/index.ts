import useFetch from '@/hooks/useFetch';

export default function BookApis() {
  const { postFetch } = useFetch();

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
    endDate: null | string | 0;
    startDate: null | string | 0;
    childUuid: null | string;
  }) => {
    const res = await postFetch({
      api: '/allowance/day',
      data: data,
    });
    return res;
  };

  const postDayCash = async (data: {
    date: string;
    usage: string;
    amount: number;
  }) => {
    const res = await postFetch({
      api: '/allowance/cash',
      data: data,
    });
    return res;
  };

  const postReceipt = async (data: {
    file: File | null;
    transactionUuid: string;
    type: string;
  }) => {
    const res = await postFetch({
      api: '/allowance/cash',
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
  };
}
