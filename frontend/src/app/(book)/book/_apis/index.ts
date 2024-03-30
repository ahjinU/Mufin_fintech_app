import useFetch from '@/hooks/useFetch';

export default function BookApis() {
  const { postFetch } = useFetch();

  const postMonthBook = async (data: {
    startDate: string;
    endDate: string;
    childUuid: null | string;
  }) => {
    console.log(data);
    const res = await postFetch({
      api: '/allowance/calender-detail',
      data: { data },
    });
    return res;
  };

  return { postMonthBook };
}
