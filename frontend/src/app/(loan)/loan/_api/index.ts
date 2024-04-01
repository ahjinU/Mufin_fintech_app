import useFetch from '@/hooks/useFetch';

export default function LoanAPI() {
  const { postFetch } = useFetch();

  const getLoanDetail = async (data: { loanUuid: string }) => {
    const res = await postFetch({
      api: '/loan/detail/child',
      data: data,
    });
    return res;
  };

  return { getLoanDetail };
}
